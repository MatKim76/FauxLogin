#!/bin/bash

## Exemple : ./Ecouteur.sh 1234 "echo 'Le processus est terminé'"

sleep 5

# Vérifie si les arguments nécessaires ont été fournis
if [ -z "$1" ] || [ -z "$2" ]
then
    echo "Veuillez fournir un nom de processus ou un ID de processus et une commande à exécuter en arguments."
    exit 1
fi

# Essaye de récupérer l'ID du processus à partir du nom du processus
pid=$(pgrep $1)

# Si pgrep ne trouve rien, suppose que l'argument est un ID de processus
if [ -z "$pid" ]
then
    pid=$1
fi

# Vérifie si le processus existe
if ! kill -0 $pid 2> /dev/null
then
    echo "Le processus $pid n'existe pas."
    exit 1
fi

# Boucle qui vérifie périodiquement si le processus est toujours en cours d'exécution
while kill -0 $pid 2> /dev/null
do
    sleep 1
done

# Exécute la commande spécifiée
$2

# Affiche un message lorsque le processus se termine
echo "Le processus $pid a terminé son exécution."
