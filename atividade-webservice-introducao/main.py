from flask import Flask, render_template
from flask_socketio import SocketIO

app = Flask(__name__)
app.config['SECRET_KEY'] = 'RUhZfFtdvYbH0kJM6yFc2EfxoxsRU8v5'
socketio = SocketIO(app)


@app.route('/')
def index():
    return render_template('index.html')


def messageReceived(methods=['GET', 'POST']):
    print('message was received!!!')


def assembleSentence(data):
    array = []
    for i in data["array"]:
        quantity = 0
        for j in i["alunos"]:
            if j["matriculado"] == True:
                quantity += 1
        array.append(
            "<p>A turma {} de {} do curso {} possui {} alunos, dos quais {} estão devidamente matriculados.</p>".format(i["turma"], i["ano"], i["curso"], len(i["alunos"]), quantity))
    return array


@socketio.on('send')
def handle_new_user(data, methods=['GET', 'POST']):
    response = assembleSentence(data)
    socketio.emit('result', response, callback=messageReceived)


if __name__ == '__main__':
    socketio.run(app, debug=True, port=4000)
