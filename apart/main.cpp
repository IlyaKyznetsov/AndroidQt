#include <QAndroidService>

#include <QDebug>

//C:/Android/Sdk/platform-tools/adb.exe   logcat -e qt
int main(int argc, char *argv[])
{
  qDebug() << "qt Hello from service";
  QAndroidService app(argc, argv);
  qWarning() << "qt --- Starting APART ---";
  return app.exec();
}
