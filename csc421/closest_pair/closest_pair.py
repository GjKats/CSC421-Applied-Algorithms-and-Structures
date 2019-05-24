import math
import os


def create_points(f):
    points = []
    lines = f.readlines()
    for i in lines:
        points.append((int(i.split()[0]), int(i.split()[1])))
    return points


def closest_pair(points):
    points.sort(key=lambda x: x[0])

    if len(points) < 4:
        return closest_pair_base_case(points)

    else:
        m = len(points) // 2
        l_points = points[:m]
        r_points = points[m:]
        mid = (l_points[-1][0] + r_points[0][0]) / 2
        closest_l = closest_pair(l_points)
        closest_r = closest_pair(r_points)
        closest_l_and_r = min(closest_l, (closest_r[0], closest_r[1] + m, closest_r[2] + m))
        closest = min(closest_l_and_r, closest_pair_strip_case(points, mid, closest_l_and_r[0]))

    if closest[1] > closest[2]:
        return closest[0], closest[2], closest[1]
    else:
        return closest


def closest_pair_strip_case(points, mid, closest_dist):
    strip = [point for point in points if abs(point[0] - mid) < closest_dist]
    strip.sort(key=lambda x: x[1])
    k = len(strip)
    closest = float('inf')
    closest_i = -1
    closest_j = -1

    for i in range(k - 1):
        for j in range((i + 1), min([i + 3, k - 1]) + 1):
            distance = math.sqrt(
                (strip[i][0] - strip[j][0]) ** 2 + (strip[i][1] - strip[j][1]) ** 2)
            if distance < closest:
                closest = distance
                closest_i = points.index(strip[i])
                closest_j = points.index(strip[j])

    return closest, closest_i, closest_j


def closest_pair_base_case(points):

    closest_dist = float('inf')
    closest_i = -1
    closest_j = -1

    for i in range(len(points)):
        for j in range((i + 1), len(points)):
            current_dist = math.sqrt((points[i][0] - points[j][0]) ** 2 + (points[i][1] - points[j][1]) ** 2)
            if current_dist < closest_dist:
                closest_dist = current_dist
                closest_i = i
                closest_j = j

    return closest_dist, closest_i, closest_j


def run():
    files = os.listdir()
    for file in files:
        if "10" in file:
            f = open(file, "r")
            p = create_points(f)
            output = closest_pair(p)
            p.sort(key=lambda x: x[0])
            print("The minimum distance for {} is: {}: {} <---> {}".format(f.name, output[0], p[output[1]], p[output[2]]))


run()



