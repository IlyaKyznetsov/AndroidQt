#include "DriverControl.h"

#include <QDebug>
#define F() qWarning() << __PRETTY_FUNCTION__
#define D(x) qWarning() << x
#define DF(x) qWarning() << __PRETTY_FUNCTION__ << x

#include <signal.h>

DriverControl::DriverControl()
{
  F();
  setupUi(this);
  connect(okButton, &QPushButton::clicked, [](){F(); throw 1;});
  connect(cancelButton, &QPushButton::clicked,  [](){F(); ::raise(SIGSEGV);});
}

DriverControl::~DriverControl()
{
  F();
}
