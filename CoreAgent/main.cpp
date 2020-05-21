#include <CoreAgent.h>
#include <android/log.h>
#include <QDebug>
#include <QThread>
// Set in my myapp.pro file for android builds
#include <android/log.h>
const char *const applicationName = "qt_LauncherActivity";
void myMessageHandler(QtMsgType type, const QMessageLogContext &context, const QString &msg)
{
  QString report = msg;
  if (context.file && !QString(context.file).isEmpty())
  {
    report += " in file ";
    report += QString(context.file);
    report += " line ";
    report += QString::number(context.line);
  }
  if (context.function && !QString(context.function).isEmpty())
  {
    report += +" function ";
    report += QString(context.function);
  }
  const char *const local = report.toLocal8Bit().constData();
  switch (type)
  {
    case QtDebugMsg: __android_log_write(ANDROID_LOG_DEBUG, applicationName, local); break;
    case QtInfoMsg: __android_log_write(ANDROID_LOG_INFO, applicationName, local); break;
    case QtWarningMsg: __android_log_write(ANDROID_LOG_WARN, applicationName, local); break;
    case QtCriticalMsg: __android_log_write(ANDROID_LOG_ERROR, applicationName, local); break;
    case QtFatalMsg:
    default: __android_log_write(ANDROID_LOG_FATAL, applicationName, local); abort();
  }
}

int main(int argc, char *argv[])
{
  /*CoreAgent*/ QAndroidService app(argc, argv);
  static QString TAG = "qt_LauncherActivity";
  qInstallMessageHandler(myMessageHandler);
  QThread thread;
  int zz = 0;
  __android_log_write(ANDROID_LOG_INFO,
                      TAG.toLatin1().data(),
                      "qt waiting... ++++++++-----------------------------------");
  qInfo() << "QQuickViewer setup completed  qt -----------------------------++++++++";

  QObject::connect(&thread, &QThread::started, [&]() {
    __android_log_write(ANDROID_LOG_INFO,
                        TAG.toLatin1().data(),
                        "qt waiting... ++++++++-----------------------------------");
    qDebug() << "qt waiting... -----------------------------------";
    QThread::msleep(5000);
    qDebug() << "qt done---------------------------------++++++++";
    int aa = 10 / zz;
    app.exit(aa);
    qDebug() << "qt app.quit---------------------------------++++++++";
  });
  thread.start();

  qWarning() << "qt ++++++++ CoreAgent ++++++++";
  qDebug() << "qt ++++++++ CoreAgent ++++++++++++++++";
  app.exec();
  thread.wait();
  return 0;
}
