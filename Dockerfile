FROM MySQL:latest
ENV MYSQL_DATABASE: my_blog
ENV MYSQL_USER: blogger
ENV MYSQL_PASSWORD: travel
ENV MYSQL_ROOT_PASSWORD: secret
EXPOSE 3307:3307