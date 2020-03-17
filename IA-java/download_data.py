#!/usr/bin/env python
# coding: utf-8

import os
import subprocess
import pandas as pd
import numpy as np
import json
print("Import completed")


#############################################################
#           FUNCTIONS
#############################################################

def save_json(my_dict, filename):
    with open(filename, "w", encoding="utf-8") as file: #"iso8859-1"
        json.dump(my_dict, file, indent=2, allow_nan=False, ensure_ascii=False, sort_keys = True)


def extract_html_list_content(name, year):
    first_line = '''class="lista_ostatnich_losowan"'''
    last_line = '''<!-- lista_ostatnich_losowan -->'''
    filename = "HTML/" + year + "-wyniki-"+name+".html"
    #print(filename)
    with open(file=filename, mode="r", encoding="utf-8") as file:
        content = file.read()
    #print("content",content)
    content = content.split(first_line)[1].split(last_line)[0]   
    return content


def extract_html_lists_elements(content):
    ul_list = content.split('</ul>')
    text = 'ul style="position: relative;">'
    ul_list = [el.split(text)[1] for el in ul_list if 'ul' in el]
    return ul_list


def extract_values_create_dict(ul_list):
    results_dict = {}
    results_list = []
    for ul in ul_list:
        li_list = ul.split('</li>')
        li_dict = {}
        li_dict['numbers'] = []
        for li in li_list:
            if 'numbers' in li:
                li_dict['numbers'].append(str(li.split('>')[1].strip().strip('.')))
            if 'date' in li:
                li_dict['date'] = li.split('>')[1].strip().strip('.')
            if 'nr' in li:
                li_dict['id'] = int(li.split('>')[1].strip().strip('.'))
        # print(li_dict)
        results_list.append(li_dict)
        results_dict[li_dict['id']] = li_dict
    return results_list, results_dict


def refresh_data (name, first_year, last_year):
    whole_results_list = []
    for i in range(first_year, last_year+1):
        year = str(i)
        content = extract_html_list_content(name, year)
        ul_list = extract_html_lists_elements(content)
        results_list_year, results_dict_year = extract_values_create_dict(ul_list)
        # print("year: ", year, "dict :", len(results_dict_year),
        # print("list :", len(results_list_year))
        whole_results_list.extend(results_list_year)
    print("Values extracted")
    save_json(whole_results_list, name+"-history.json")
    print("Values saved to json files")

##############################################
#               MAIN
##############################################
# refresh html
subprocess.run(["sh", "download.sh"])

####################################################
# extract content
refresh_data ("lotto", 1957, 2020)
refresh_data ("mini-lotto", 1981, 2020)

