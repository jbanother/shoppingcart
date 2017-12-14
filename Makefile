.PHONY: build

build:
	docker build -t rawkode/shoppingcart:latest .

interactive:
	docker run --rm -it --entrypoint=bash -v $(PWD):/code -w /code -u root gradle:jdk8
