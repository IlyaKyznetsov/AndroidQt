#include "DeliveryAgent.h"

#include <QDebug>
#define F() qWarning() << __PRETTY_FUNCTION__
#define D(x) qWarning() << x
#define DF(x) qWarning() << __PRETTY_FUNCTION__ << x

//#include <asm/signal.h>
#include <signal.h>

#ifndef Q_QDOC
DeliveryAgent::DeliveryAgent(int &argc, char **argv, int flags) : QAndroidService(argc, argv, flags)
{
  DF(argc);
//   raise(SIGSEGV);
}
DeliveryAgent::DeliveryAgent(int &argc, char **argv,
                     const std::function<QAndroidBinder *(const QAndroidIntent &intent)> &binder, int flags)
    : QAndroidService(argc, argv, binder, flags)
{
  DF(argc);
}
#else
DeliveryAgent(int &argc, char **argv);
DeliveryAgent(int &argc, char **argv, const std::function<QAndroidBinder *(const QAndroidIntent &intent)> &binder);
#endif

DeliveryAgent::~DeliveryAgent()
{
  F();
}
