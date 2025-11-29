// 1. DB接続情報の外部化が望ましいですが、ここでは一旦そのまま使用します。
    //    【⚠️ 接続情報がハードコードされている問題は残ります】
    String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
    String dbUser = "root";
    String dbPassword = "root";

    // トランザクション処理を開始し、Connectionをtry-with-resourcesで自動解放
    try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
        
        // 【重要】オートコミットを無効にし、手動でトランザクションを管理します
        connection.setAutoCommit(false); 
        
        try {
            // ----------------------------------------------------
            // 1. 子テーブル (skills) のレコードを先に削除します
            // ----------------------------------------------------
            String deleteSkillsSql = "DELETE FROM skills WHERE user_id = ?";
            try (PreparedStatement deleteSkillsStatement = connection.prepareStatement(deleteSkillsSql)) {
                deleteSkillsStatement.setInt(1, userId);
                deleteSkillsStatement.executeUpdate();
            }

            // ----------------------------------------------------
            // 2. 親テーブル (users) のレコードを削除します
            // ----------------------------------------------------
            String deleteUserSql = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement deleteUserStatement = connection.prepareStatement(deleteUserSql)) {
                deleteUserStatement.setInt(1, userId);
                deleteUserStatement.executeUpdate();
            }

            // 3. 全ての操作が成功した場合のみ、コミットします
            connection.commit();

        } catch (SQLException e) {
            // 途中でエラーが発生した場合、ロールバックして変更を取り消します
            connection.rollback();
            // 例外を再スローし、クライアントにエラーを伝えます
            throw e; 
        }