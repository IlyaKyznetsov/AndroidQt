#include <QAndroidService>
//#include <QApplication>
#include <QDebug>

int main(int argc, char *argv[])
{
    QAndroidService a(argc, argv);
//    QApplication a(argc, argv);
    qCritical()<<"QAndroidService";
    return a.exec();
}
