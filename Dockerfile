
# This is for NCC docker build
# docker: make Dockerfile | image | push docker registry
# https://oss.navercorp.com/workbox/workbox-api/issues/1735

FROM java:8
ADD target/springboot-demo.jar /springboot-demo.jar
RUN bash -c 'touch /springboot-demo.jar'
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/springboot-demo.jar"]
MAINTAINER wang.qingsong
