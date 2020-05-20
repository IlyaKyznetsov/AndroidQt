#include "PushButton.h"
#include <QLabel>
#include <QShortcut>
#include <QStyle>
#include <QGridLayout>
#include <QVariant>
#include <QSpacerItem>
/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
PushButton::PushButton(QWidget *parent) : QPushButton(parent)
{
  setObjectName("PushButton");
  _warning = _error = false;
  _textLabel = new QLabel();
  _textLabel->setObjectName("text");
  _textLabel->setAlignment(Qt::AlignCenter);
  _shortHintLabel = new QLabel();
  _shortHintLabel->setObjectName("shortHint");
  _shortHintLabel->setAlignment(Qt::AlignCenter);
  _shortcut = new QShortcut(parent);
  _shortcut->setAutoRepeat(false);
  _shortHintLabel->clear();
  _shortcut->setEnabled(false);
  connect(_shortcut, &QShortcut::activated, this, [this]() { animateClick(100); });

  QGridLayout *l = new QGridLayout(this);
  l->setRowStretch(0, 1);
  l->setRowStretch(1, 2);
  l->setRowStretch(2, 1);
  l->addItem(new QSpacerItem(0, 0, QSizePolicy::Minimum, QSizePolicy::QSizePolicy::Expanding), 0, 1, 1, 1);
  l->addItem(new QSpacerItem(0, 0, QSizePolicy::Minimum, QSizePolicy::QSizePolicy::Expanding), 1, 1, 1, 1);
  l->addItem(new QSpacerItem(0, 0, QSizePolicy::Minimum, QSizePolicy::QSizePolicy::Expanding), 2, 1, 1, 1);
  l->addWidget(_shortHintLabel,0,0);
  l->addWidget(_textLabel,1,0);
  l->addWidget(new QLabel(),2,0);
}

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
PushButton::PushButton(const QString &text, QWidget *parent) : PushButton(parent)
{
  _textLabel->setText(text);
}

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
PushButton::PushButton(const QString &text, const QString &shortlabel, const QKeySequence &shortcut, QWidget *parent)
    : PushButton(text, parent)
{
  _shortHint = shortlabel;
  _shortcut->setKey(shortcut);
}

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
QString PushButton::text() const
{
  return _textLabel->text();
}

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
void PushButton::setText(const QString &text)
{
  _textLabel->setText(text);
}

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
QString PushButton::shortHint() const
{
  return _shortHintLabel->text();
}

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
void PushButton::setShortHint(const QString &shortHint)
{
  _shortHint = shortHint;
}

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
void PushButton::setShortcut(const QKeySequence &shortcut)
{
  const bool enabled = _shortcut->isEnabled();
  _shortcut->setKey(shortcut);
  _shortcut->setEnabled(enabled);
}

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
bool PushButton::isWarning() const
{
  return _warning;
}

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
void PushButton::setWarning(bool warning)
{
  if (warning != _warning)
  {
    _warning = warning;
    setProperty("warning", _warning);
    style()->unpolish(this);
    style()->polish(this);
  }
}

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
bool PushButton::isError() const
{
  return _error;
}

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
void PushButton::setError(bool error)
{
  if (error != _error)
  {
    _error = error;
    setProperty("error", _error);
    style()->unpolish(this);
    style()->polish(this);
  }
}

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
bool PushButton::isShortcutEnabled() const
{
  return _shortcut->isEnabled();
}

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
void PushButton::setShortcutEnabled(bool enabled)
{
  if (enabled)
    _shortHintLabel->setText(_shortHint);
  else
    _shortHintLabel->clear();
  _shortcut->setEnabled(enabled);
}
