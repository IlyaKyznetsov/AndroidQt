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
  connect(okButton, &QPushButton::click, this, &DriverControl::term);
  connect(cancelButton, &QPushButton::click,  this, &DriverControl::term);
}

DriverControl::~DriverControl()
{
  F();
}

void DriverControl::showEvent(QShowEvent *event)
{
  DF(event);
  QDialog::showEvent(event);
}

void DriverControl::hideEvent(QHideEvent *event)
{
  DF(event);
  QDialog::hideEvent(event);
}

void DriverControl::term()
{
    F();
    throw 1;
}
