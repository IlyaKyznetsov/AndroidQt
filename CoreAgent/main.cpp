#include <Global.h>
#include <QAndroidService>
#include <QThread>

static void signal_handler(int signal)
{
  /* Display a message indicating we have received a signal */
  //if (signal == SIGUSR1)
  printf("Received a SIGUSR1 signal\n");
  qDebug() << "qt signal_handler :" << signal;
  /* Exit the application */

  exit(signal);
}

class Application : public QAndroidService
{
  public:
  Application(int &argc, char **argv) : QAndroidService(argc, argv) {}

  virtual bool notify(QObject *receiver, QEvent *e) override
  {
    bool result = false;
    try
    {
      result = QAndroidService::notify(receiver, e);
    }
    catch (std::exception &ex)
    {
      qDebug() << "qt Необработанное исключение:" << ex.what();
    }
    catch (...)
    {
      qFatal("qt Неопознанное необработанное исключение!");
    }

    return result;
  }
};

int main(int argc, char *argv[])
{
  /* Register the signal handler */
  //for (int signum = 0; signgam < 256; signum++)
  ::signal(SIGSEGV, signal_handler);
  ::signal(SIGFPE, signal_handler);
  ::signal(SIGINT, signal_handler);
  ::signal(SIGTERM, signal_handler);

  //  /*CoreAgent*/ QAndroidService app(argc, argv);
  Application app(argc, argv);

  static QString TAG = "qt_LauncherActivity";
  QThread thread;
  int zz = 1;
  qInfo() << "QQuickViewer setup completed  qt -----------------------------++++++++";

  QObject::connect(&thread, &QThread::started, [&]() {
    try
    {
      qDebug() << "qt waiting... -----------------------------------";
      QThread::msleep(5000);
      qDebug() << "qt done---------------------------------++++++++";
      int aa = 10 / zz;
      app.exit(aa);
      qDebug() << "qt app.quit---------------------------------++++++++";
    }
    catch (std::exception &ex)
    {
      qInfo() << "exception в потоке: " << ex.what();
    }
  });
  thread.start();

  qDebug() << "qt ++++++++ CoreAgent ++++++++++++++++";
  int result = 0;
  try
  {
    result = app.exec();

    thread.quit();
    result += thread.wait();
    qDebug() << "qt ++++++++ QUIT CoreAgent ++++++++++++++++";
    app.quit();
    ::raise(SIGSEGV);
    throw SIGSEGV;
    return result;
  }
  catch (...)
  {
    qInfo() << "Ошибка при запуске программы: ";
  }
  return 666;
}
