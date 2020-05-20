#ifndef COREAGENT_H
#define COREAGENT_H

#include "CoreAgent_global.h"
#include <QAndroidService>
#include <QAndroidIntent>

class COREAGENT_EXPORT CoreAgent : public QAndroidService
{
public:
#ifndef Q_QDOC
  CoreAgent(int &argc, char **argv, int flags = ApplicationFlags);
  CoreAgent(int &argc, char **argv, const std::function<QAndroidBinder *(const QAndroidIntent &intent)> &binder,
            int flags = ApplicationFlags);
#else
  CoreAgent(int &argc, char **argv);
  CoreAgent(int &argc, char **argv, const std::function<QAndroidBinder *(const QAndroidIntent &intent)> &binder);
#endif

  virtual ~CoreAgent();
};

#endif // COREAGENT_H
