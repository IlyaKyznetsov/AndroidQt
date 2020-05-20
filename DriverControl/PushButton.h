#ifndef PUSHBUTTON_H
#define PUSHBUTTON_H
/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*\
 Программа   : Контроль и управление
 Модуль      : Класс кнопки с поддержкой shortcut с подсказками
 Файл        : PushButton.h
 Разработчик : Кузнецов И.В., 2018г.
\*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
#include <QPushButton>
class QLabel;
class QShortcut;
class PushButton : public QPushButton
{
  Q_OBJECT
  Q_PROPERTY(QString shortHint READ shortHint WRITE setShortHint)
  Q_PROPERTY(bool warning READ isWarning WRITE setWarning)
  Q_PROPERTY(bool error READ isError WRITE setError)
  // Q_PROPERTY(bool enabled READ isEnabled WRITE setEnabled) from QPushButton
  // Q_PROPERTY(QString text READ text WRITE setText) from QPushButton
public:
  explicit PushButton(QWidget *parent);
  explicit PushButton(const QString &text, QWidget *parent);
  explicit PushButton(const QString &text, const QString &shortHint, const QKeySequence &shortcut, QWidget *parent);
  ~PushButton() = default;
  bool isWarning() const;
  bool isError() const;
  void setShortcut(const QKeySequence &shortcut);
  bool isShortcutEnabled() const;
  QString text() const;
  QString shortHint() const;

public slots:
  void setWarning(bool warning);
  void setError(bool error);
  void setShortcutEnabled(bool enabled);
  void setText(const QString &text);
  void setShortHint(const QString &shortHint);

private:
  bool _warning, _error;
  QString _shortHint;
  QLabel *_textLabel, *_shortHintLabel;
  QShortcut *_shortcut;
};

#endif // PUSHBUTTON_H
