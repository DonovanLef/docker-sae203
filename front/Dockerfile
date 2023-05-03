FROM nginx:latest

COPY ./html/ /var/www
COPY ./nginx_config.txt /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["./exec.bash"]