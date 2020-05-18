#include <QDebug>
#include <DeliveryAgent.h>

int main(int argc, char *argv[])
{
    DeliveryAgent app(argc,argv);
    qWarning()<<"++++++++ DeliveryAgent ++++++++";
    return app.exec();
}
