module org.example.safescamapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.pdfbox;
    requires java.desktop;
    requires org.apache.commons.io;



    // Открываем пакеты для FXMLLoader
    opens org.example.safescamapp to javafx.fxml;
    opens org.example.safescamapp.controllers to javafx.fxml;
    opens org.example.safescamapp.fxml to javafx.fxml;  // Если FXML файлы в отдельном пакете

    // Экспортируем основные пакеты
    exports org.example.safescamapp;
    exports org.example.safescamapp.controllers;
}
