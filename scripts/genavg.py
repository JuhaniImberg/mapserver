#!/usr/bin/env python2
# -*- coding: utf-8 -*-

import csv
import os
import subprocess
import argparse
import glob
import math


def average(s):
    return sum(s) / len(s)


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("param1", type=str, default=u"MaissiVesi")
    parser.add_argument("param2", type=str, default=u"MaissiLämpö")
    args = parser.parse_args()

    try:
        os.mkdir("../parseout/")
    except OSError:
        pass

    ind1 = None
    ind2 = None
    listof = []
    liststr = []
    listofpurkka = []

    with open("../src/main/resources/parameters.csv") as fp:
        tx = fp.read().split("\n")
        ind1 = tx[0].split(",").index(args.param1)
        ind2 = tx[0].split(",").index(args.param2)
        tx.pop(0)
        for i in tx:
            try:
                listof.append(
                    [float(i.split(",")[ind1]), float(i.split(",")[ind2])])
            except IndexError:
                pass

    last = None
    vals = []
    ind = 0
    for f in glob.glob("../out/*/*/population.csv"):
        fname = f
        spl = f.split("/")[-3:-1]
        fold = spl[0]
        if fold != last:
            if len(vals) != 0:
                summ = sum(vals)
                avg = average(vals)
                #variance = map(lambda x: (x - avg) ** 2, vals)
                #standard_deviation = math.sqrt(average(variance))
                try:
                    listofpurkka.append(avg)
                    liststr.append(
                        "%s, %s, %f" % (listof[ind - 1][0], listof[ind - 1][1], avg))
                except IndexError:
                    pass
                    #(ind, listof[ind - 1][0], listof[ind - 1][1], avg, average(variance), standard_deviation))
            vals = []
            ind += 1
            last = fold
        with open(fname, "r") as fp:
            vals.append(sum(list(map(float, list(fp)[-1].split(",")))))
            fp.close()

    print("min, %s, %s, %f" %
          (min(listof, key=lambda x: x[0])[0], min(listof, key=lambda x: x[1])[1], min(listofpurkka)))
    print("max, %s, %s, %f" %
          (max(listof, key=lambda x: x[0])[0], max(listof, key=lambda x: x[1])[1], max(listofpurkka)))

    for i in liststr:
        print i


if __name__ == "__main__":
    main()
