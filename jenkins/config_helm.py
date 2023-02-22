import yaml

with open('k8s_app/values_backup.yaml', 'r') as f:
    values_backup = yaml.safe_load(f)
with open('k8s_app/values.yaml', 'w') as f:
    yaml.safe_dump(values_backup, f)

# Charger le fichier YAML
with open('whanos.yml', 'r') as f:
    whanos = yaml.safe_load(f)
with open('k8s_app/values.yaml', 'r') as f:
    values = yaml.safe_load(f)

# Modifier les valeurs pour les clés données
kind = whanos['kind']
if kind != 'Deployment':
    exit (1)

values['replicaCount'] = whanos['spec']['replicas']

values['resources'] = whanos['spec']['template']['spec']['containers'][0]['resources']

values['deployment'] = whanos['spec']['template']['spec']['containers'][0]['ports']

if len(values['deployment']) != 0:
    values['service']['enabled'] = True
    values['service']['type'] = "LoadBalancer"

# Enregistrer les modifications dans le fichier YAML
with open('k8s_app/values.yaml', 'w') as f:
    yaml.safe_dump(values, f)