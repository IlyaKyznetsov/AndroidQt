#include "Global.h"

#include <QDebug>
#include <QFile>
#include <QStandardPaths>

void Logging(const QString &str)
{
  QString path = QStandardPaths::writableLocation(QStandardPaths::DownloadLocation); // DownloadLocation
  //  if (!QDir(path).exists())    QDir().mkdir(path);

  auto fileName = path + "/logTest.txt";

  QFile file(fileName);
  if (file.open(QIODevice::Append))
  {
    file.write(str.toLocal8Bit().data());
    file.write("\n");
  }
  file.close();
}
