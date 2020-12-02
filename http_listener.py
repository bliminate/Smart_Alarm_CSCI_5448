from flask import Flask, request

# Instantiate an app object
app = Flask(__name__)

# Add features to this app object
@app.route('/', methods=['POST'])
def result():
    print("water", request.form['water']) # should display 'bar'
    print("ground", request.form['ground']) # should display 'bar'
    return 'Received !' # response to your request.


@app.route('/')
def index():
    return('Hello world')


# Start the app
if __name__ == '__main__':
    app.run(debug=True, host='127.0.0.1', port=8000)

