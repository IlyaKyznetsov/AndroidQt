#include <QAndroidService>
#include <QApplication>
#include <QDebug>

int main(int argc, char *argv[])
{
//  if (argc <= 1) {
//      // code to handle main activity execution
//      QApplication app(argc, argv);
//      qWarning()<<"main activity execution";
//      return app.exec();
//  } else if (argc > 1 && strcmp(argv[1], "service") == 0) {
//      QAndroidService app(argc, argv);
//      qWarning() << "Service starting with from the same .so file";
//      return app.exec();
//  } else {
//      qWarning() << "Unrecognized command line argument";
//      return -1;
//  }
    QAndroidService app(argc, argv);
    qWarning() << "Service starting with from the same .so file";
    return app.exec();
}
