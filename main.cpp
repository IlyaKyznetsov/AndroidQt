//#include <QtAndroid>
//#include <QAndroidIntent>
//#include <QAndroidJniObject>

#include <QAndroidService>


#include <QDebug>

int main(int argc, char *argv[])
{

  QAndroidService app(argc, argv);
  qWarning() << "Service starting with from the same .so file";
  return app.exec();
}
