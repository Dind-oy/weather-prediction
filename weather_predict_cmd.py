import sys
import joblib
import pandas as pd

# Validasi argumen
if len(sys.argv) != 5:
    print("Usage: <precipitation> <temp_max> <temp_min> <wind>")
    sys.exit(1)

# Memuat model dengan path absolut
NB = joblib.load('NB_model.pkl')

# Input dari command line
precipitation = float(sys.argv[1])
temp_max = float(sys.argv[2])
temp_min = float(sys.argv[3])
wind = float(sys.argv[4])

# Input dengan nama fitur
features = pd.DataFrame([[precipitation, temp_max, temp_min, wind]],
                        columns=["precipitation", "temp_max", "temp_min", "wind"])

# python weather_predict_cmd.py 4.1 4.4 0.6 5.3
# Prediksi cuaca
print(NB.predict(features)[0])
