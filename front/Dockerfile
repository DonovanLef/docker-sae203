FROM nginx:latest

RUN  apt-get update

COPY ./exec.bash /bin/exec.bash
COPY ./html/ /var/www
COPY ./nginx_config.txt /etc/nginx/conf.d/default.conf

WORKDIR /bin

EXPOSE 80
#CMD ["./exec.bash"]