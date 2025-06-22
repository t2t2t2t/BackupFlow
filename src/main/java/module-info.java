module org.example.safescamapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.pdfbox;
    requires java.desktop;
    requires org.apache.commons.io;



    // Открываем пакеты для FXMLLoader
    opens backuplow to javafx.fxml;
    opens backuplow.controllers to javafx.fxml;
    opens backuplow.fxml to javafx.fxml;  // Если FXML файлы в отдельном пакете

    // Экспортируем основные пакеты
    exports backuplow;
    exports backuplow.controllers;
}
