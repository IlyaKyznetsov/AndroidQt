//#include <QtAndroid>
//#include <QAndroidIntent>
//#include <QAndroidJniObject>

#include <QAndroidService>


#include <QDebug>
#include <QFile>
#include <QStandardPaths>

void log(const QString &str)
{
  QString path = QStandardPaths::writableLocation(QStandardPaths::DownloadLocation); // DownloadLocation
  //  if (!QDir(path).exists())    QDir().mkdir(path);

  auto fileName = path + "/logTest.txt";

  QFile file(fileName);
  if (file.open(QIODevice::Append))
  {
    file.write(str.toLocal8Bit().data());
    file.write("\n");
  }
  file.close();
}

int main(int argc, char *argv[])
{
  qWarning() << "Service starting with from the same .so file";

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
  log("1 Service starting with from the same.so file opera");
  QAndroidService app(argc, argv);
  qWarning() << "Qt Service starting with from the same .so file";
  log("Qt Service starting with from the same.so file opera");

  return app.exec();
}
