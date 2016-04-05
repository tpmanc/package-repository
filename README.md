# Diplom

## Дела
 1. ~~Добавить в БД размер файла~~
 2. ~~После загрузки файла и перед добавлением: проверка по hash; если он совпадает, то сравниваем размер~~
 3. ~~При переносе файла из временной папки в нужную добавить в путь часть из hash~~
 4. ~~Попробовать загрузку большого файла~~
 5. ~~Сколько угодно деревьев категорий: возможно в БД хранить json для каждого дерева~~
 6. ~~Заменить инпут для загрузки на дропзон~~
 7. ~~Загрузка папок (может в архив + текстовый файл с содержимым)~~
 8. ~~Сделать разделение на фронт и бэк~~
 9. ~~Привязка своих свойств к файлам~~
 10. ~~Авторизацию через Active Directory~~
 11. ~~Создание, удаление, изменение своих свойств файлов~~
 12. ~~Добавить дату добавления версии~~
 13. ~~Удаление категории~~
 14. ~~Добавить к версии файла дату загрузки~~
 15. ~~Переделать коннекшн к БД через пул~~
 16. ~~Сделать все через maven~~
 17. ~~Перенести название файла в таблицу `file`, версию в `fileVersion`~~
 18. ~~После загрузки файла выдавать окно по заполнению информации о файле~~
 19. ~~Добавить кнопку скачать версию~~
 20. ~~Удаление свойства файла~~
 21. ~~employeeID использовать как логин~~
 22. ~~Искать во всех группах домена~~
 23. ~~На странице просмотра файла добавить выбор версии~~
 24. ~~Пагинация в просмотре файлов~~
 25. ~~Удаление свойства версии~~
 26. ~~Добавление свойства версии~~
 27. ~~Редактирование свойства версии~~
 28. ~~Получение роли из AD~~
 29. ~~Вынести настройки БД и, наверно, Active Directory в отдельный файл `.properties`~~
 30. ~~Пргонять свойства через перекодировку, чтобы вырезать нечитаемые символы~~
 31. ~~Сделать чтобы админ видел все незаполненные файлы~~
 32. ~~Страница, где все незаполненные файлы юзера~~
 33. ~~Добавить к связь с юзером, который залил файл~~
 34. ~~Страница юзера~~
 35. ~~При первом логине заносить инфу в табличку~~
 36. ~~В свойствах версии можно выбрать свойство дважды~~
 37. ~~Валидация свойств файла и версии~~
 38. ~~Привязка файла к категориям~~
 39. ~~Перенести все layout в отдельную папку~~
 40. ~~Обычным юзерам оставить только "каталог" и "отправить заявку"~~
 41. ~~Версия файла хранится текстом и поэтому плохо сортируется~~
 42. ~~Поиск~~
 43. ~~Сделать нормальную страницу ошибки 404, 403~~
 44. ~~Переместить по папкам виды и js~~
 45. ~~Написать комментарии к коду~~
 46. ~~Логирование всех операций~~
 47. ~~Возможность просмотра логов по уровням, удаления логов~~
 48. ~~Добавить в незаполненные файлы что незаполнено: свойства или категории~~
 49. Экспорт версии файла
 50. Добавить заявки на добавление для обычных пользователей
 51. Сделать проверку при логине: если properties не заполнены, то перенаправлять на страницу заполнения
 52. Добавить в БД для заявки поле комментарий и заполнять его при отконении или принятии
 53. Добавить настроку пути для сохранения файлов и файлов заявок
 54. Удалить fax, isDisable из категории
 55. В logModel,RequestModel,FileVersionModel добавить getUser; fileCategory - getFile getCategory; FilePropertyModel & FileVersionPropertyModel - getProperty()
; FileVersionModel - getFile()
 56. Переделать логи на syslog
 57. Сохранени и загрузка шаблонов экспорта