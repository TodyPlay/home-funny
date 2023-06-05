FROM nginx as home-funny-front

COPY target/dist /dist
COPY nginx.conf.template /etc/nginx/conf.d/default.conf

EXPOSE 80

ENTRYPOINT ["nginx", "-g" ,"daemon off;"]