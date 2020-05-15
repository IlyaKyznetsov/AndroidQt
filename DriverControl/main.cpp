#include <QApplication>
#include <QDialog>

int main(int argc, char *argv[])
{
    QApplication app(argc, argv);
    QDialog driverControl;
    driverControl.show();
    return app.exec();
}
