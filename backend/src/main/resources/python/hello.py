import numpy as np
import pandas as pd


import seaborn as sns
import matplotlib.pyplot as plt
import missingno as msno


from sklearn.linear_model import LinearRegression
from sklearn.tree import DecisionTreeRegressor
from sklearn.ensemble import RandomForestRegressor, GradientBoostingRegressor


from sklearn.pipeline import Pipeline, FeatureUnion
from sklearn.base import BaseEstimator, TransformerMixin
from sklearn.impute import SimpleImputer
from sklearn.preprocessing import OneHotEncoder, LabelEncoder
from sklearn.compose import ColumnTransformer

from sklearn.model_selection import train_test_split, cross_val_score, GridSearchCV
from sklearn.metrics import accuracy_score, mean_squared_error, make_scorer, mean_absolute_error

sns.set_theme()

df_train = pd.read_csv('C:/Users/Alex/Desktop/06022023/web/backend/src/main/resources/python/datasets/train.csv')

df_train.describe()

def print_missing_values(df):
    missing_values_df = pd.DataFrame(columns=['dtype','Feature', 'Number Of Missing Values', 'Percentage of Missing values'])

    for i in df:
        if df[i].isnull().sum() != 0:
            dtype = df[i].dtype
            feature                      = i
            number_of_missing_values     = df[i].isnull().sum()
            percentage_of_missing_values = round(number_of_missing_values / len(df.index) * 100, 2)
            missing_values_df = missing_values_df.append({
                'dtype': dtype,
                'Feature': i,
                'Number Of Missing Values': number_of_missing_values,
                'Percentage of Missing values': percentage_of_missing_values
            }, ignore_index=True)

    return missing_values_df

print_missing_values(df_train)

print('kekw')


