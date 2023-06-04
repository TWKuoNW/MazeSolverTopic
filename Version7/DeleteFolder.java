package Version7;

import java.io.File;

public class DeleteFolder {
    public static void main(String[] args) {
        String folderPath = "path/to/folder";

        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            deleteFolder(folder);
            System.out.println("資料夾刪除成功");
        } else {
            System.out.println("資料夾不存在或不是一個資料夾");
        }
    }

    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    file.delete();
                }
            }
        }

        folder.delete();
    }
}

