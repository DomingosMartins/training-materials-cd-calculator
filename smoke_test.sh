#!/bin/bash
test `curl 192.168.1.4:8080/sum?a=1\\&b=2` -eq 3
