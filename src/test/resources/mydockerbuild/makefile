build:
	@docker-compose -p jenkins build 
run:
	@docker-compose -p jenkins up -d nginx data master hub node nodeff 
stop:
	@docker-compose -p jenkins stop
clean:	stop
	@docker-compose -p jenkins rm master nginx node nodeff hub
clean-data: clean
	@docker-compose -p jenkins rm -v data
clean-images:
	@docker rmi `docker images -q -f "dangling=true"`
