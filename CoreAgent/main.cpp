#include <Global.h>
#include <QAndroidService>

int main(int argc, char *argv[])
{
  QAndroidService app(argc, argv);
  DF("++++++++ QAndroidService CoreAgent ++++++++");
  return app.exec();
}
