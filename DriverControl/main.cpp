#include <QApplication>
#include <QFile>
#include <DriverControl.h>

int main(int argc, char *argv[])
{
    QApplication app(argc, argv);

    QFile style(":/style.css");
    style.open(QIODevice::ReadOnly);
    app.setStyleSheet(style.readAll());
    style.close();

    DriverControl kernel;
    kernel.showFullScreen();
    return app.exec();
}
