#ifndef COREAGENT_GLOBAL_H
#define COREAGENT_GLOBAL_H

#include <QtCore/qglobal.h>

#if defined(COREAGENT_LIBRARY)
#  define COREAGENT_EXPORT Q_DECL_EXPORT
#else
#  define COREAGENT_EXPORT Q_DECL_IMPORT
#endif

#endif // COREAGENT_GLOBAL_H
