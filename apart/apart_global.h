#ifndef APART_GLOBAL_H
#define APART_GLOBAL_H

#include <QtCore/qglobal.h>

#if defined(APART_LIBRARY)
#  define APART_EXPORT Q_DECL_EXPORT
#else
#  define APART_EXPORT Q_DECL_IMPORT
#endif

#endif // APART_GLOBAL_H
