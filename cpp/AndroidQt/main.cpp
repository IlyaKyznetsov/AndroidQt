#include <QAndroidService>
#include <QApplication>

#include "Global.h"

#include <QDialog>

int main(int argc, char *argv[])
{
  D("### Arguments ###");
  Logging("### Arguments ###");
  for (int i = 0; i != argc; ++i)
    D(argv[i]);

  if (argc > 1)
  {
    const QString name(argv[1]);
    D("###" << name << "###");
    if ("core_agent" == name)
    {
      QAndroidService app(argc, argv);
      D("--- Starting core_agent ---");
      return app.exec();
    }
    else if ("delivery_agent" == name)
    {
      QAndroidService app(argc, argv);
      D("--- Starting delivery_agent ---");
      return app.exec();
    }
    else if ("driver_control" == name)
    {
      QApplication app(argc, argv);
      D("--- Starting driver_control ---");
      QDialog driverControl;
      driverControl.show();
      return app.exec();
    }
    else
    {
      goto TERMINATE_APPLICATION;
    }
  }

TERMINATE_APPLICATION:
  qWarning() << "Unrecognized command line argument";
  return -1;
}
