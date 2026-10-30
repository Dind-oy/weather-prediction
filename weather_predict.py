import joblib
import pandas as pd

# Memuat model
NB = joblib.load('NB_model.pkl')

# Input prediksi dengan nama fitur
input_data = pd.DataFrame([[0.0, 12.8, 5.0, 4.7]], 
                          columns=["precipitation", "temp_max", "temp_min", "wind"])

# Prediksi cuaca
prediction = NB.predict(input_data)[0]
print(prediction)
