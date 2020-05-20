#ifndef DELIVERYAGENT_H
#define DELIVERYAGENT_H

#include "DeliveryAgent_global.h"
#include <QAndroidIntent>
#include <QAndroidService>

class DELIVERYAGENT_EXPORT DeliveryAgent : public QAndroidService
{
public:
#ifndef Q_QDOC
  DeliveryAgent(int &argc, char **argv, int flags = ApplicationFlags);
  DeliveryAgent(int &argc, char **argv, const std::function<QAndroidBinder *(const QAndroidIntent &intent)> &binder,
                int flags = ApplicationFlags);
#else
  DeliveryAgent(int &argc, char **argv);
  DeliveryAgent(int &argc, char **argv, const std::function<QAndroidBinder *(const QAndroidIntent &intent)> &binder);
#endif

  virtual ~DeliveryAgent();
};

#endif // DELIVERYAGENT_H
