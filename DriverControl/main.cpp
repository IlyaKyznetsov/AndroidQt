#include <DriverControl.h>
#include <QApplication>
#include <QDebug>
#include <QFile>
static void signal_handler(int signal)
{
  /* Display a message indicating we have received a signal */
  //if (signal == SIGUSR1)
  printf("Received a SIGUSR1 signal\n");
  qDebug() << "qt signal_handler :" << signal;
  /* Exit the application */

  exit(signal);
}

int main(int argc, char *argv[])
{
  ::signal(SIGSEGV, signal_handler);
  ::signal(SIGFPE, signal_handler);
  ::signal(SIGINT, signal_handler);
  ::signal(SIGTERM, signal_handler);

  QApplication app(argc, argv);

  QFile style(":/style.css");
  style.open(QIODevice::ReadOnly);
  app.setStyleSheet(style.readAll());
  style.close();

  DriverControl kernel;
  kernel.showFullScreen();
  return app.exec();
}
