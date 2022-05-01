FROM maven:3.8.4-openjdk-8-slim

WORKDIR ~

ADD ./target/product-0.0.1-SNAPSHOT.jar  ./product.jar
	
ENTRYPOINT ["java", "-jar"]

CMD ["product.jar"]

EXPOSE 8071