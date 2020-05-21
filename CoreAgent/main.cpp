#include <QDebug>
#include <CoreAgent.h>

int main(int argc, char *argv[])
{
    CoreAgent app(argc,argv);
    qWarning()<<"++++++++ CoreAgent ++++++++";
    return app.exec();
}
