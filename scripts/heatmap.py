#!/usr/bin/env python

import math
import cairo
import random
import argparse

parser = argparse.ArgumentParser()
parser.add_argument("file", type=argparse.FileType("r"))
args = parser.parse_args()

WIDTH, HEIGHT = 256, 256

surface = cairo.ImageSurface (cairo.FORMAT_ARGB32, WIDTH, HEIGHT)
ctx = cairo.Context (surface)

##ctx.scale (WIDTH, HEIGHT) # Normalizing the canvas

ctx.set_source_rgb(1, 1, 1)
ctx.rectangle(0, 0, WIDTH, HEIGHT)
ctx.fill()

def drawHeat(x, y, s, rectWidth = 1, rectHeight = 1):
	ctx.set_source_rgb (float(s), 0., float(1.-s));
	ctx.rectangle (x, y, rectWidth, rectHeight);
	ctx.fill ();

s = 0
paramAMax = None
paramAMin = None
paramBMax = None
paramBMin = None
heatMax = None
heatMin = None
t = None
with args.file as fp:
	t = fp.read().split("\n")
	paramAMin = float(t[0].split(",")[1])
	paramBMin = float(t[0].split(",")[2])
	heatMin = float(t[0].split(",")[3])
	paramAMax = float(t[1].split(",")[1])
	paramBMax = float(t[1].split(",")[2])
	heatMax = float(t[1].split(",")[3])

t.pop(0)
t.pop(0)

# remaps value to 0-1
def remap(val, lower, higher):
	return (val-lower)/(higher-lower);

def distance(x, y, ox, oy):
	dx = x-ox
	dy = y-oy
	return math.sqrt(dx*dx + dy*dy)

xArr = []
yArr = []
heatArr = []

for i in t:
	la = i.split(",")
	if len(la) == 1:
		break
	xArr.append( remap(float(la[0]), paramAMin, paramAMax) * WIDTH )
	yArr.append( remap(float(la[1]), paramBMin, paramBMax) * HEIGHT )
	heatArr.append( remap(float(la[2]), heatMin, heatMax) )

cumulHeatMax = 0
def findCumulHeatForPixel(x, y):
	global cumulHeatMax
	global xArr
	global yArr
	global heatArr
	cumulHeat = 0
	for i in range(len(heatArr)):
		try:
			cumulHeat += heatArr[i] / math.sqrt(distance(x, y, xArr[i], yArr[i]))
		except:
			pass
	if cumulHeat > cumulHeatMax:
		cumulHeatMax = cumulHeat
	return cumulHeat



for i in range(len(heatArr)):
	drawHeat(xArr[i], yArr[i], heatArr[i], 3, 3)

ctx.set_source_rgb (0, 255, 0);
ctx.rectangle(WIDTH/2, 0, 1, HEIGHT)
ctx.fill ();
ctx.rectangle(0, HEIGHT/2, WIDTH, 1)
ctx.fill ();

surface.write_to_png ("example.png") # Output to PNG

