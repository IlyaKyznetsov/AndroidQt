#ifndef DRIVERCONTROL_H
#define DRIVERCONTROL_H

#include "DriverControl_global.h"
#include <QDialog>
#include "ui_MainForm.h"

class DRIVERCONTROL_EXPORT DriverControl : public QDialog, private Ui::MainForm
{
    Q_OBJECT
public:
    DriverControl();
    ~DriverControl();
};

#endif // DRIVERCONTROL_H
