create database tp_favorites;
use tp_favorites;
create user 'tp_favorites'@'localhost' identified by 'tp_favorites';
grant all privileges on tp_favorites.* to 'tp_favorites'@'localhost';