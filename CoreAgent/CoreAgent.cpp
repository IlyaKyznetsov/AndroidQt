#include "CoreAgent.h"

#include <QDebug>
#define F() qWarning() << __PRETTY_FUNCTION__
#define D(x) qWarning() << x
#define DF(x) qWarning() << __PRETTY_FUNCTION__ << x

#ifndef Q_QDOC
CoreAgent::CoreAgent(int &argc, char **argv, int flags) : QAndroidService(argc, argv, flags)
{
  DF(argc);
//   throw 1;
}
CoreAgent::CoreAgent(int &argc, char **argv,
                     const std::function<QAndroidBinder *(const QAndroidIntent &intent)> &binder, int flags)
    : QAndroidService(argc, argv, binder, flags)
{
  DF(argc);
//   throw 1;
}
#else
CoreAgent(int &argc, char **argv);
CoreAgent(int &argc, char **argv, const std::function<QAndroidBinder *(const QAndroidIntent &intent)> &binder);
#endif

CoreAgent::~CoreAgent()
{
  F();
}

QAndroidBinder *CoreAgent::onBind(const QAndroidIntent &intent)
{
    F();
    QAndroidBinder* binder=QAndroidService::onBind(intent);
//  DF(intent.handle().toString());
    return binder;
}
