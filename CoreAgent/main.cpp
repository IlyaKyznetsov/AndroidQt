#include <QAndroidService>
#include <QDebug>

int main(int argc, char *argv[])
{
    QAndroidService app(argc,argv);
    qWarning()<<"++++++++ CoreAgent ++++++++";
    return app.exec();
}
