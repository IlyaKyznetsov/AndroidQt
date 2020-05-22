#include <Global.h>
#include <QAndroidService>
#include <QThread>
int main(int argc, char *argv[])
{
  /*CoreAgent*/ QAndroidService app(argc, argv);
  static QString TAG = "qt_LauncherActivity";
  QThread thread;
  int zz = 0;
  qInfo() << "QQuickViewer setup completed  qt -----------------------------++++++++";

  QObject::connect(&thread, &QThread::started, [&]() {
    qDebug() << "qt waiting... -----------------------------------";
    QThread::msleep(5000);
    qDebug() << "qt done---------------------------------++++++++";
    int aa = 10 / zz;
    app.exit(aa);
    qDebug() << "qt app.quit---------------------------------++++++++";
  });
  thread.start();

  qDebug() << "qt ++++++++ CoreAgent ++++++++++++++++";
  int result = app.exec();

  thread.quit();
  result += thread.wait();
  return result;
}
